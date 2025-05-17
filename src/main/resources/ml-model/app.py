'''
from flask import Flask, request, jsonify
from model.disease_predictor import predict_disease, train_model
from model.recommender import get_recommendations

app = Flask(__name__)

# Only once to train and generate model.pkl
train_model()

@app.route("/predict", methods=["POST"])
def predict():
    data = request.json
    symptoms = data.get("symptoms")

    if not symptoms or not isinstance(symptoms, list):
        return jsonify({"error": "Please provide a list of symptoms"}), 400

    disease = predict_disease(symptoms)
    recommendations = get_recommendations(disease)

    return jsonify({
        "predicted_disease": disease,
        "recommendations": recommendations
    })

if __name__ == "__main__":
    app.run(debug=True)
'''

'''
from flask import Flask, request, jsonify
import joblib
import numpy as np
import os

app = Flask(__name__)

# Load the trained model
model_path = os.path.join("model", "gradient_boosting_model.pkl")
model = joblib.load(model_path)

# Replace this with the actual list of all symptom features used during training
all_symptoms = [
    'fever', 'headache', 'nausea', 'vomiting', 'cough', 'fatigue',
    'skin_rash', 'joint_pain', 'itching', 'sore_throat',
    # ... add all symptom features from your training CSV
]

@app.route('/predict', methods=['POST'])
def predict():
    try:
        data = request.get_json()
        symptoms = data.get("symptoms", [])

        # Convert symptoms to binary input vector
        input_vector = np.zeros(len(all_symptoms))
        for symptom in symptoms:
            if symptom in all_symptoms:
                index = all_symptoms.index(symptom)
                input_vector[index] = 1

        # Make prediction
        prediction = model.predict([input_vector])[0]

        return jsonify({
            "success": True,
            "predicted_disease": str(prediction)
        })

    except Exception as e:
        return jsonify({
            "success": False,
            "error": str(e)
        }), 500

if __name__ == "__main__":
    app.run(debug=True)
    
'''

'''
from flask import Flask, request, jsonify
import joblib
import numpy as np
import json

app = Flask(__name__)
model = joblib.load("gradient_boosting_model.pkl")

# This list should match the column order in your training data
with open("symptoms_list.json", "r") as f:
    all_symptoms_list = json.load(f)

@app.route("/predict", methods=["POST"])
def predict():
    data = request.get_json()
    symptoms = data.get("symptoms", [])

    input_vector = [0] * len(all_symptoms_list)
    for symptom in symptoms:
        if symptom in all_symptoms_list:
            index = all_symptoms_list.index(symptom)
            input_vector[index] = 1

    prediction = model.predict([input_vector])[0]

    # Example dummy response (replace with actual logic if needed)
    response = {
        "disease": str(prediction),
        "medications": ["Paracetamol", "Antibiotics"],
        "diet": ["Soup", "Fruits"],
        "description": "Take rest and hydrate well.",
        "precautions": ["Avoid cold drinks", "Take medicine on time"],
        "workout": "Light stretching, avoid overexertion"
    }
    return jsonify(response)

if __name__ == "__main__":
    app.run(debug=True)
'''
'''
from flask import Flask, request, jsonify
import joblib
import numpy as np
import json

app = Flask(__name__)
model = joblib.load("gradient_boosting_model.pkl")

# This list should match the column order in your training data
with open("symptoms_list.json", "r") as f:
    all_symptoms_list = json.load(f)
    
@app.route('/predict', methods=['POST'])
def predict():
    data = request.get_json()
    symptoms = data.get("symptoms", [])

    input_vector = prepare_input(symptoms)
    prediction = model.predict(input_vector)[0]

    # You map predicted disease to recommendations (medications, diet, etc.)
    disease_name = inverse_label_map[prediction]  # reverse label encoding

    result = {
        "predicted_disease": disease_name,
        "medications": get_medications(disease_name),
        "precautions": get_precautions(disease_name),
        "diet": get_diet(disease_name),
        "workout": get_workout(disease_name)
    }
    return jsonify(result)
'''

'''
from flask import Flask, request, jsonify
import joblib
import numpy as np
import json

app = Flask(__name__)

# Load the trained model
model = joblib.load("gradient_boosting_model.pkl")

# Load list of all symptoms (must match training features order)
with open("symptoms_list.json", "r") as f:
    all_symptoms_list = json.load(f)

# Load label mapping (index to disease name)
with open("label_map.json", "r") as f:
    inverse_label_map = json.load(f)

# Function to convert input symptoms into one-hot encoded vector
def prepare_input(symptoms):
    input_vector = [1 if symptom in symptoms else 0 for symptom in all_symptoms_list]
    return [input_vector]  # 2D list for model input

# Placeholder function: You can replace with real data or DB queries
def get_medications(disease):
    meds = {
        "Diabetes": ["Metformin", "Insulin"],
        "Malaria": ["Artemisinin", "Chloroquine"],
        "Heart Disease": ["Aspirin", "Atorvastatin"],
        "COVID-19": ["Paracetamol", "Rest", "Hydration"],
    }
    return meds.get(disease, [])

def get_precautions(disease):
    precautions = {
        "Diabetes": ["Avoid sugar", "Regular exercise"],
        "Malaria": ["Use mosquito nets", "Avoid stagnant water"],
        "Heart Disease": ["Low-fat diet", "Avoid stress"],
        "COVID-19": ["Wear mask", "Isolate if infected"],
    }
    return precautions.get(disease, [])

def get_diet(disease):
    diet = {
        "Diabetes": ["Low sugar foods", "High fiber"],
        "Malaria": ["Iron-rich foods", "Hydration"],
        "Heart Disease": ["Low sodium", "Leafy greens"],
        "COVID-19": ["High protein", "Vitamins"],
    }
    return diet.get(disease, [])

def get_workout(disease):
    workout = {
        "Diabetes": ["Walking", "Yoga"],
        "Malaria": ["Rest until recovery"],
        "Heart Disease": ["Light cardio", "Walking"],
        "COVID-19": ["Breathing exercises", "Rest"],
    }
    return workout.get(disease, [])

# API route for prediction
@app.route('/predict', methods=['POST'])
def predict():
    try:
        data = request.get_json()
        symptoms = data.get("symptoms", [])

        input_vector = prepare_input(symptoms)
        prediction = model.predict(input_vector)[0]

        disease_name = inverse_label_map[str(prediction)]

        result = {
            "predicted_disease": disease_name,
            "medications": get_medications(disease_name),
            "precautions": get_precautions(disease_name),
            "diet": get_diet(disease_name),
            "workout": get_workout(disease_name)
        }
        return jsonify(result)
    
    except Exception as e:
        return jsonify({"error": str(e)}), 500

# Health check route
@app.route('/ping', methods=['GET'])
def ping():
    return jsonify({"message": "Flask API is running ✅"})

if __name__ == '__main__':
    app.run(debug=True)

'''
'''
from flask import Flask, request, jsonify
import pandas as pd
import numpy as np
import joblib

app = Flask(__name__)

# Load model and other files
try:
    model = joblib.load("disease_prediction_model.pkl")  # Use the correct path
except Exception as e:
    print(f"Error loading model: {e}")

symptom_list = list(pd.read_csv("data/Training.csv").drop(columns=["prognosis"]).columns)

# Load additional datasets
medications_df = pd.read_csv("data/medications.csv")
diets_df = pd.read_csv("data/diets.csv")

@app.route("/predict", methods=["POST"])
def predict():
    data = request.get_json()
    input_symptoms = data.get("symptoms", [])

    # Create input vector
    input_vector = [1 if symptom in input_symptoms else 0 for symptom in symptom_list]
    input_array = np.array(input_vector).reshape(1, -1)

    try:
        # Predict disease using the model
        prediction = model.predict(input_array)[0]
    except Exception as e:
        return jsonify({"error": f"Error during prediction: {e}"}), 500

    # Fetch medication and diet recommendations
    medication_row = medications_df[medications_df["Disease"] == prediction]
    diet_row = diets_df[diets_df["Disease"] == prediction]

    medications = eval(medication_row["Medication"].values[0]) if not medication_row.empty else []
    diet_plan = eval(diet_row["Diet"].values[0]) if not diet_row.empty else []

    return jsonify({
        "predicted_disease": prediction,
        "recommended_medicines": medications,
        "recommended_diet": diet_plan
    })

if __name__ == "__main__":
    app.run(debug=True)
'''

'''
from flask import Flask, request, jsonify
import pandas as pd
import numpy as np
import joblib

app = Flask(__name__)

model = None
symptom_list = []
medications_df = pd.DataFrame()
diets_df = pd.DataFrame()

try:
    model = joblib.load("disease_prediction_model.pkl")  
    print("Model loaded successfully.")
except Exception as e:
    print(f"Error loading model: {e}")

try:
    symptom_list = list(pd.read_csv("data/Training.csv").drop(columns=["prognosis"]).columns)
    print("Symptom list loaded successfully.")
except Exception as e:
    print(f"Error loading symptom list from Training.csv: {e}")

try:
    medications_df = pd.read_csv("data/medications.csv")
    diets_df = pd.read_csv("data/diets.csv")
    print("Medication and diet datasets loaded successfully.")
except Exception as e:
    print(f"Error loading medication or diet data: {e}")

@app.route("/predict", methods=["POST"])
def predict():
	
	if model is None:
    	raise RuntimeError("Failed to load ML model - server cannot start")

	if not symptom_list:
    	raise RuntimeError("Failed to load symptom list - check Training.csv")

	if medications_df.empty or diets_df.empty:
    	raise RuntimeError("Failed to load medication/diet datasets")
    	
    if model is None:
        return jsonify({"error": "Model not loaded properly"}), 500

    data = request.get_json()
    input_symptoms = data.get("symptoms", [])

    if not symptom_list:
        return jsonify({"error": "Symptom list not available"}), 500

    input_vector = [1 if symptom in input_symptoms else 0 for symptom in symptom_list]
    input_array = np.array(input_vector).reshape(1, -1)

    try:
        prediction = model.predict(input_array)[0]
    except Exception as e:
        return jsonify({"error": f"Error during prediction: {e}"}), 500

    medication_row = medications_df[medications_df["Disease"] == prediction]
    diet_row = diets_df[diets_df["Disease"] == prediction]

    medications = eval(medication_row["Medication"].values[0]) if not medication_row.empty else []
    diet_plan = eval(diet_row["Diet"].values[0]) if not diet_row.empty else []

    return jsonify({
        "predicted_disease": prediction,
        "recommended_medicines": medications,
        "recommended_diet": diet_plan
    })

if __name__ == "__main__":
    app.run(debug=True)
   '''
   
'''
from flask import Flask, request, jsonify
import pandas as pd
import numpy as np
import joblib
from ast import literal_eval
import logging
from flask_limiter import Limiter
from flask_limiter.util import get_remote_address

app = Flask(__name__)
app.logger.setLevel(logging.INFO)

limiter = Limiter(
    app=app,
    key_func=get_remote_address,
    default_limits=["200 per day", "50 per hour"]
)

model = None
symptom_list = []
medications_df = pd.DataFrame()
diets_df = pd.DataFrame()

def load_resources():
    """Load ML model and required datasets with proper validation"""
    global model, symptom_list, medications_df, diets_df
    
    try:
        model = joblib.load("disease_prediction_model.pkl")
        app.logger.info("Model loaded successfully")
    except Exception as e:
        app.logger.error(f"Model loading failed: {str(e)}")
        raise RuntimeError("Model loading failed") from e

    try:
        training_data = pd.read_csv("data/Training.csv")
        symptom_list = list(training_data.drop(columns=["prognosis"]).columns)
        app.logger.info(f"Loaded {len(symptom_list)} symptoms")
    except Exception as e:
        app.logger.error(f"Symptom list loading failed: {str(e)}")
        raise RuntimeError("Symptom data loading failed") from e

    try:
        medications_df = pd.read_csv("data/medications.csv")
        diets_df = pd.read_csv("data/diets.csv")

        for df, name in [(medications_df, 'medications'), (diets_df, 'diets')]:
            if 'Disease' not in df.columns:
                raise ValueError(f"{name} CSV missing 'Disease' column")
            
        app.logger.info("Medication and diet data loaded")
    except Exception as e:
        app.logger.error(f"Medication/diet loading failed: {str(e)}")
        raise RuntimeError("Medication/diet data loading failed") from e

@app.route("/predict", methods=["POST"])
@limiter.limit("10/minute")  
def predict():
    """Handle prediction requests with proper validation"""
    if model is None:
        return jsonify({"error": "Prediction service unavailable"}), 503

    try:
        data = request.get_json()
        if not data or 'symptoms' not in data:
            return jsonify({"error": "Invalid request format"}), 400
            
        input_symptoms = data['symptoms']
        if not isinstance(input_symptoms, list):
            return jsonify({"error": "Symptoms should be a list"}), 400

        input_vector = [1 if symptom in input_symptoms else 0 for symptom in symptom_list]
        input_array = np.array(input_vector).reshape(1, -1)

        prediction = model.predict(input_array)[0]
        app.logger.info(f"Prediction made: {prediction}")

        medication_info = medications_df[medications_df["Disease"] == prediction]
        diet_info = diets_df[diets_df["Disease"] == prediction]

        medications = []
        if not medication_info.empty:
            try:
                medications = literal_eval(medication_info["Medication"].values[0])
            except (SyntaxError, ValueError) as e:
                app.logger.warning(f"Medication parsing error: {str(e)}")

        diet_plan = []
        if not diet_info.empty:
            try:
                diet_plan = literal_eval(diet_info["Diet"].values[0])
            except (SyntaxError, ValueError) as e:
                app.logger.warning(f"Diet parsing error: {str(e)}")

        return jsonify({
            "predicted_disease": prediction,
            "recommended_medicines": medications,
            "recommended_diet": diet_plan
        })

    except Exception as e:
        app.logger.error(f"Prediction error: {str(e)}")
        return jsonify({"error": "Internal server error"}), 500

@app.route("/health")
def health_check():
    """Endpoint for service health monitoring"""
    status = {
        "model_loaded": model is not None,
        "symptoms_loaded": len(symptom_list) > 0,
        "medications_loaded": not medications_df.empty,
        "diets_loaded": not diets_df.empty
    }
    return jsonify(status)

if __name__ == "__main__":
    try:
        load_resources()
        if model is None:
            raise RuntimeError("Model not loaded")
        if not symptom_list:
            raise RuntimeError("Symptoms not loaded")
        if medications_df.empty or diets_df.empty:
            raise RuntimeError("Medication/diet data not loaded")
            
        app.run(host='0.0.0.0', port=5000, debug=False)  
    except RuntimeError as e:
        app.logger.critical(f"Failed to start server: {str(e)}")
        exit(1)
'''

# app.py

from flask import Flask, request, jsonify
import pickle
import pandas as pd
import numpy as np

app = Flask(__name__)

# Load the model and label encoder
with open('model.pkl', 'rb') as file:
    model = pickle.load(file)

with open('label_encoder.pkl', 'rb') as file:
    label_encoder = pickle.load(file)

# Load dataframes for recommendations
description_df = pd.read_csv("data/description.csv")
diets_df = pd.read_csv("data/diets.csv")
medications_df = pd.read_csv("data/medications.csv")
precautions_df = pd.read_csv("data/precautions_df.csv")
workout_df = pd.read_csv("data/workout_df.csv")
symptom_severity_df = pd.read_csv("data/Symptom-severity.csv")

# Clean symptom names (remove underscores and make lowercase)
symptom_severity_df['Symptom'] = symptom_severity_df['Symptom'].str.replace('_', ' ').str.lower()

# Create a list of all symptoms
all_symptoms = symptom_severity_df['Symptom'].unique().tolist()

@app.route('/predict', methods=['POST'])
def predict():
    try:
        data = request.get_json()
        symptoms = data['symptoms']  # Expecting a list of symptoms

        # 1. Prepare input for the model
        input_data = [1 if symptom in symptoms else 0 for symptom in all_symptoms]
        input_data = np.array(input_data).reshape(1, -1)  # Reshape for the model

        # 2. Make Prediction
        predicted_encoded = model.predict(input_data)[0]
        predicted_disease = label_encoder.inverse_transform([predicted_encoded])[0]

        # 3. Get Recommendations
        disease_name = predicted_disease  # Use the predicted disease name

        description = description_df[description_df['Disease'] == disease_name]['Description'].values[0] if disease_name in description_df['Disease'].values else "Description not found"
        diet_recommendations = diets_df[diets_df['Disease'] == disease_name]['Diet_Recommendation'].values.tolist() if disease_name in diets_df['Disease'].values else ["Diet recommendations not found"]
        medication_recommendations = medications_df[medications_df['Disease'] == disease_name]['Medication_Recommendation'].values.tolist() if disease_name in medications_df['Disease'].values else ["Medication recommendations not found"]
        precautions_list = precautions_df[precautions_df['Disease'] == disease_name][['Precaution_1', 'Precaution_2', 'Precaution_3', 'Precaution_4']].values.tolist()[0] if disease_name in precautions_df['Disease'].values else ["Precaution not found"]
        workout_recommendations = workout_df[workout_df['disease'] == disease_name]['workout'].values.tolist() if disease_name in workout_df['disease'].values else ["Workout recommendations not found"]

        # 4. Return Results
        result = {
            'disease': disease_name,
            'description': description,
            'diet_recommendations': diet_recommendations,
            'medication_recommendations': medication_recommendations,
            'precautions': precautions_list,
            'workout_recommendations': workout_recommendations
        }

        return jsonify(result)

    except Exception as e:
        return jsonify({'error': str(e)})

if __name__ == '__main__':
    app.run(debug=True)

