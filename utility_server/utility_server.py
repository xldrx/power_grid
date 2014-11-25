from flask import Flask, request
import json
import shared.models
from shared.models import RPS

app = Flask(__name__)


@app.route('/')
def hello_world():
    ret = {'message': 'Hello World! (Mamad)'}
    return json.dumps(ret)


@app.route('/add-rps/<name>')
def add_rps(name):
    rps_name = name
    rps_value = 12
    # rps_name = request.form["name"]
    # rps_value = request.form["value"]
    record = RPS.create(name=rps_name, timing=rps_value)
    record.save()
    return "OK"


if __name__ == '__main__':
    shared.models.init()
    app.run("0.0.0.0", 8881, debug=True)
