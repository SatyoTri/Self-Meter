import tensorflow as tf

model = tf.keras.models.load_model('app/models/mental_health_model.h5')

labels = {
    0: "ADHD",
    1: "ASD",
    2: "Loneliness",
    3: "MDD",
    4: "OCD",
    5: "PDD",
    6: "PTSD",
    7: "Anxiety",
    8: "Bipolar",
    9: "Eating Disorder",
    10: "Psychotic Depression",
    11: "Sleeping Disorder",
    12: "Inconclusive"
}