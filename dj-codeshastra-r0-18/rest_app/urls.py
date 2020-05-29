from django.conf.urls import url
from . import views

urlpatterns = [
    url (r'signup/', views.signup, name="signup"),
    url (r'login/', views.login, name="login"),
    url (r'toll_locations/', views.locations, name="toll_locations"),
    url (r'qrgen/', views.qrgen, name="qrgen"),
    url (r'payment/', views.payment, name="payment"),
    url (r'auth/', views.auth, name="auth"),
    url (r'details/', views.userdetails, name="details"),
    url (r'payment2/', views.pay2, name="pay2")
]
