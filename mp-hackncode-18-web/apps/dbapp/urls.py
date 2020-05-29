from django.urls import path, re_path, include

from .views import *

from rest_framework.routers import DefaultRouter

# NOTE: django-rest-framework url routing
router = DefaultRouter ()
router.register('lines', LineViewSet)
router.register('trains', TrainViewSet)

urlpatterns = [
    path ('', include (router.urls)),
    path ('auth/', include('rest_framework.urls')),
    path ('status/', get_feedback)
]
