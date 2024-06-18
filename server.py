import os
from flask import Flask
from flask_cors import CORS
from app.routes.predictions import prediction_bp
from app.routes.questions import questions_bp

def create_app():
    app = Flask(__name__)
    CORS(app)
    app.register_blueprint(prediction_bp)
    app.register_blueprint(questions_bp)
    return app

app = create_app()

if __name__ == '__main__':
    port = int(os.environ.get("PORT", 8080))  # Default to port 8080 if PORT is not set
    app.run(host="0.0.0.0", port=port, debug=os.environ.get("FLASK_DEBUG", "False") == "True")
