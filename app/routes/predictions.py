from flask import Blueprint, request, jsonify
import numpy as np
from datetime import datetime
from app.models.model import model, labels
from app.storeData.firestore import FirestoreClient

prediction_bp = Blueprint('prediction', __name__)
firestore_client = FirestoreClient()
EXPECTED_LENGTH = 28

@prediction_bp.route('/predict', methods=['POST'])
def predict():
    try:
        input_data = request.json.get('input_data')
        if not input_data:
            return jsonify({'error': 'Input data is missing.'}), 400
        if not isinstance(input_data, list) or len(input_data) != EXPECTED_LENGTH:
            return jsonify({'error': 'Invalid input data format or length.'}), 400
        
        input_data = np.array([input_data])
        prediction = model.predict(input_data)
        predicted_label = labels[np.argmax(prediction)]
        predicted_percentages = (prediction[0] / prediction[0].sum()) * 100

        response = {
            'id': np.random.randint(1000, 9999),
            'predicted_label': predicted_label,
            'predicted_percentages': {labels[i]: percentage.item() for i, percentage in enumerate(predicted_percentages)},
            'createdAt': datetime.now().strftime('%Y-%m-%d %H:%M:%S')
        }

        firestore_client.save_prediction('predictions', str(response['id']), response)

        return jsonify(response), 200

    except Exception as e:
        return jsonify({'error': str(e)}), 500
