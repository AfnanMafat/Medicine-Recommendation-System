# # # # Step 1: Install required packages
# # # # pip install pandas scikit-learn numpy joblib

# # # # Step 2: Model Creation (medicine_recommender.py)
# # # import pandas as pd
# # # from sklearn.feature_extraction.text import TfidfVectorizer
# # # from sklearn.multioutput import MultiOutputClassifier
# # # from sklearn.ensemble import RandomForestClassifier
# # # from sklearn.pipeline import Pipeline
# # # from sklearn.model_selection import train_test_split
# # # import joblib

# # # # Load dataset
# # # df = pd.read_csv('ML-Project-Help.csv')

# # # # Preprocessing
# # # # Combine all symptoms into a single string for each row
# # # df['Symptoms'] = df['Symptoms'].str.lower().str.replace(', ', ' ')
# # # df['Medicine'] = df['Recommended Medicine'].str.split('(').str[0].str.strip()
# # # df['Diet'] = df['Diet Recommendation']

# # # # Create features and targets
# # # X = df['Symptoms']
# # # y = df[['Medicine', 'Diet']]

# # # # Split data
# # # X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# # # # Create pipeline
# # # model = Pipeline([
# # #     ('tfidf', TfidfVectorizer(max_features=500)),
# # #     ('clf', MultiOutputClassifier(
# # #         RandomForestClassifier(n_estimators=200, class_weight='balanced')
# # #     ))
# # # ])

# # # # Train model
# # # model.fit(X_train, y_train)

# # # # Evaluate
# # # print(f"Accuracy: {model.score(X_test, y_test):.2f}")

# # # # Save model
# # # joblib.dump(model, 'medical_model.joblib')

# # import pandas as pd
# # from sklearn.feature_extraction.text import TfidfVectorizer
# # from sklearn.multioutput import MultiOutputClassifier
# # from sklearn.ensemble import RandomForestClassifier
# # from sklearn.pipeline import Pipeline
# # from sklearn.model_selection import train_test_split
# # import joblib

# # # Load dataset with proper CSV formatting
# # df = pd.read_csv('ML-Project-Help.csv', quotechar='"')

# # # Clean column names by stripping quotation marks
# # df.columns = df.columns.str.strip('"')

# # # Now you can safely access the columns
# # df['Symptoms'] = df['Symptoms'].str.lower().str.replace(', ', ' ')
# # df['Medicine'] = df['Recommended Medicine'].str.split('(').str[0].str.strip()
# # df['Diet'] = df['Diet Recommendation']

# # # Rest of the code remains the same...
# # X = df['Symptoms']
# # y = df[['Medicine', 'Diet']]

# # X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# # model = Pipeline([
# #     ('tfidf', TfidfVectorizer(max_features=500)),
# #     ('clf', MultiOutputClassifier(
# #         RandomForestClassifier(n_estimators=200, class_weight='balanced')
# #     ))
# # ])

# # model.fit(X_train, y_train)
# # print(f"Accuracy: {model.score(X_test, y_test):.2f}")
# # joblib.dump(model, 'medical_model.joblib')

# import pandas as pd
# from sklearn.model_selection import train_test_split
# from sklearn.preprocessing import LabelEncoder
# from sklearn.ensemble import RandomForestClassifier
# from sklearn.metrics import accuracy_score
# import pickle

# # Load the dataset
# data = pd.read_csv('ML-Project-Help.csv')

# # Preprocessing
# # Encodes the symptoms and medicines to a numerical format
# label_enc_symptoms = LabelEncoder()
# data['encoded_symptom'] = label_enc_symptoms.fit_transform(data['"Symptoms"'])

# label_enc_medicines = LabelEncoder()
# data['encoded_medicine'] = label_enc_medicines.fit_transform(data['"Recommended Medicine"'])

# # Prepare features and labels
# X = data[['encoded_symptom']]
# y = data['encoded_medicine']

# # Split the dataset into training and testing data
# X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# # Initialize the Random Forest Classifier
# model = RandomForestClassifier(n_estimators=100, random_state=42)

# # Train the model
# model.fit(X_train, y_train)

# # Make predictions
# y_pred = model.predict(X_test)

# # Evaluate the model
# accuracy = accuracy_score(y_test, y_pred)
# print(f"Model Accuracy: {accuracy * 100:.2f}%")

# # Save the model using pickle
# with open('medicine_model.pkl', 'wb') as file:
#     pickle.dump(model, file)

# # Save encoders
# with open('label_enc_symptoms.pkl', 'wb') as file:
#     pickle.dump(label_enc_symptoms, file)

# with open('label_enc_medicines.pkl', 'wb') as file:
#     pickle.dump(label_enc_medicines, file)
import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.multioutput import MultiOutputClassifier
from sklearn.ensemble import RandomForestClassifier
from sklearn.pipeline import Pipeline
from sklearn.metrics import accuracy_score
from sklearn.preprocessing import LabelEncoder
from flask import Flask, request, jsonify

# 1. Load data from the cleaned CSV
df = pd.read_csv('clean_symptom_data.csv')  # ✅ Use cleaned CSV

# 2. Strip column names just in case and extract features/targets
df.columns = df.columns.str.strip()
X = df['Symptoms']  # ✅ Corrected column name
y_medicine = df['Recommended Medicine']
y_diet = df['Diet Recommendation']

# 3. Encode target labels
le_med = LabelEncoder()
le_diet = LabelEncoder()
y_med_enc = le_med.fit_transform(y_medicine)
y_diet_enc = le_diet.fit_transform(y_diet)

# Combine into one array for multi-output classification
Y = np.vstack((y_med_enc, y_diet_enc)).T  # shape (n_samples, 2)

# 4. Split into training and test sets
X_train, X_test, Y_train, Y_test = train_test_split(X, Y, test_size=0.2, random_state=42)

# 5. Build the pipeline: TF-IDF + Random Forest
pipeline = Pipeline([
    ('tfidf', TfidfVectorizer(stop_words='english')),
    ('clf', MultiOutputClassifier(RandomForestClassifier(n_estimators=100, random_state=42)))
])

# Train the model
pipeline.fit(X_train, Y_train)

# 6. Evaluate performance
Y_pred = pipeline.predict(X_test)
y_test_med = Y_test[:, 0]
y_test_diet = Y_test[:, 1]
y_pred_med = Y_pred[:, 0]
y_pred_diet = Y_pred[:, 1]

acc_med = accuracy_score(y_test_med, y_pred_med)
acc_diet = accuracy_score(y_test_diet, y_pred_diet)
acc_both = accuracy_score(Y_test, Y_pred)

print(f"✅ Medicine accuracy: {acc_med:.2%}")
print(f"✅ Diet accuracy: {acc_diet:.2%}")
print(f"✅ Exact-match (both correct) accuracy: {acc_both:.2%}")

# 7. Prediction function for new symptom text
def predict_recommendations(symptoms_text):
    """
    Given a string of symptoms, return predicted (medicine, diet).
    """
    pred_enc = pipeline.predict([symptoms_text])[0]
    med = le_med.inverse_transform([pred_enc[0]])[0]
    diet = le_diet.inverse_transform([pred_enc[1]])[0]
    return med, diet

# Example usage:
med_pred, diet_pred = predict_recommendations("fever, cough, headache")
print("🔮 Prediction -> Medicine:", med_pred)
print("🍽️ Prediction -> Diet:", diet_pred)

