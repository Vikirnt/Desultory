from django.conf.urls import url
from . import views

urlpatterns = [
    url(r'^$', views.thread_list, name="Frontpage"),
    url(r'^post/(?P<pid>\d+)/$', views.thread_max, name="PostDetail"),
    url(r'^add/$', views.add_post, name="AddPost"),
]
