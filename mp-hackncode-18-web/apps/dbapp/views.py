from .serializers import *
from .models import *

from datetime import datetime
import time

from django.views import View
from django.views.decorators.csrf import csrf_exempt
from django.shortcuts import HttpResponse, get_object_or_404

from rest_framework.parsers import JSONParser
from rest_framework import viewsets
from rest_framework.decorators import api_view
from rest_framework.response import Response



class LineViewSet (viewsets.ReadOnlyModelViewSet):
    queryset = Line.objects.all()
    serializer_class = LineSerializer

class PathViewSet(viewsets.ReadOnlyModelViewSet):
    queryset = Path.objects.all()
    serializer_class = PathSerializer

class TrainViewSet(viewsets.ReadOnlyModelViewSet):
    queryset = Train.objects.all()
    serializer_class = TrainSerializer


@api_view (['POST'])
def get_feedback (request):
    dick = request.data
    
    # DATA VALIDATION.
    for x in Station.objects.all ():
        if x.name == dick ['user_station']:
            dick ['user_station'] = x
            print ("Got dick")
            break
    else:
        return Response ({
            "error": "Bad user_station."
        }, status=403)
    
    dick ['time'] = datetime.strptime (dick ['time'], '%H:%M:%S').time ()
    ob = UserFeedback (**dict (dick))
    # END OF DATA VALIDATION

    # FORM VALIDATION
    train = get_object_or_404 (Train, pk=dick ['trainid'])
    path = train.path
    
    target = 0
    # Get the actual cell
    for x in path.cells.all ():
        if x.time <= ob.time:
            target = x
            break

    if not target:
        return Response ({
            "error": "User before the time"
        }, status=403)

    if target.station != ob.user_station:
        return Response ({
            "error": "User not at station."
        }, status=403)
    

    # IF ALL PASS
    ob.save ()
    target.actual_time = ob.time
    target.save ()
    return Response ({
        "status": "OK" 
    })


"""
{
  "user_station": "Borivali",
  "time": "01:00:00",
  "trainid": 1
}
"""
