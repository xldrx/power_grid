#! /usr/bin/env python -u
# coding=utf-8
import datetime

__author__ = 'xl'

from peewee import *

db = SqliteDatabase('my_database.db', threadlocals=True)

class BaseModel(Model):
    class Meta:
        database = db

class User(BaseModel):
    username = CharField(unique=True)
    message = TextField()
    created_date = DateTimeField(default=datetime.datetime.now)
    is_active = BooleanField(default=True)

class RPS(BaseModel):
    # user = ForeignKeyField(User, related_name='username')
    name = CharField()
    timing = IntegerField()

def init():
    db.connect()
    # db.create_tables([User, RPS])
