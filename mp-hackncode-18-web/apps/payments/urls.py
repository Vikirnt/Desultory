from django.urls import path, re_path, include
from . import views

urlpatterns = [
    path ('', views.demo),
    path ('try', views.pay),
    path ('general', views.confirm),
    path ('demo', views.demo),
]
