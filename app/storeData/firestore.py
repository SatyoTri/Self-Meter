from google.cloud import firestore

class FirestoreClient:
    def __init__(self):
        self.db = firestore.Client()

    def save_prediction(self, collection_name, document_id, data):
        doc_ref = self.db.collection(collection_name).document(document_id)
        doc_ref.set(data)
