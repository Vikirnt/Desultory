from django.db import models
from django.contrib.auth.models import User
from django.utils import timezone

class Post(models.Model):

    # author = models.ForeignKey (User, on_delete=models.CASCADE, blank=True)
    title = models.CharField (max_length=200)
    text = models.TextField ()
    publish_date = models.DateTimeField (auto_now_add=True)

    def __str__ (self):
        return self.title + " | " + self.text
