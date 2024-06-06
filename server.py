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
    app.run(host="0.0.0.0", port="5000", debug=True)
