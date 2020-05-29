from rest_framework import serializers
from .models import *

# NOTE: django-rest-framework
# http://www.django-rest-framework.org/tutorial/1-serialization/

class StationSerializer(serializers.ModelSerializer):
    class Meta:
        model = Station
        fields = ('name',)

class LineSerializer(serializers.ModelSerializer):
    stations = serializers.StringRelatedField (many=True)

    class Meta:
        model = Line
        fields = ('name', 'stations')

class TimeSlotSerializer(serializers.ModelSerializer):
    station = serializers.StringRelatedField (many=False)

    class Meta:
        model = TimeSlot
        fields = ('station', 'time', 'actual_time')

class PathSerializer(serializers.ModelSerializer):
    cells = TimeSlotSerializer(many=True)
    source = serializers.StringRelatedField ()
    destination = serializers.StringRelatedField ()

    class Meta:
        model = Path
        fields = ('source', 'destination','cells')

class TrainSerializer(serializers.ModelSerializer):
    path = PathSerializer ()

    class Meta:
        model = Train
        fields = ('pk', 'name', 'path')



class UserFeedbackSerializer(serializers.ModelSerializer):
    class Meta:
        model = UserFeedback
        fields = ('trainid', 'time', 'user_station')
