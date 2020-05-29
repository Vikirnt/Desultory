from django.contrib import admin
from django.contrib.auth.admin import UserAdmin

from .models import *

admin.site.register(Line)
admin.site.register(Station)
admin.site.register(TimeSlot)
admin.site.register(Path)
admin.site.register(Train)
admin.site.register(UserFeedback)
