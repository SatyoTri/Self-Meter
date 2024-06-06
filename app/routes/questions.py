from flask import Blueprint, jsonify
from app.question.listQuestion import questions

questions_bp = Blueprint('questions', __name__)

@questions_bp.route('/getquestion', methods=['GET'])
def get_questions():
    return jsonify({'questions': questions})
