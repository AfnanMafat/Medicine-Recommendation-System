import pandas as pd

# Load the training CSV
df = pd.read_csv("Training.csv")

# Extract all symptom columns
symptoms = df.columns[:-1].tolist()  # All columns except the last one (prognosis)

# Optional: Save symptoms to a text file
with open("symptoms_list.txt", "w") as file:
    for symptom in symptoms:
        file.write(symptom + "\n")

# Print the list (optional)
print("Total Symptoms:", len(symptoms))
print(symptoms)
