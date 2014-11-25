from flask import Flask
import json

app = Flask(__name__)


@app.route('/')
def hello_world():
    return 'Hello World! (%s)' % (__name__)


if __name__ == '__main__':
    app.run("0.0.0.0", 8882, debug=True)
