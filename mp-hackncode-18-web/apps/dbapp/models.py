from django.db import models
from django.db.models.signals import post_save
from django.dispatch import receiver

# Trains and stuff

class Line(models.Model):
    name = models.CharField(max_length=128)

    def __str__(self):
        return f"{self.name}"

class Station(models.Model):
    name = models.CharField(max_length=1024)
    notation = models.CharField(max_length=16)
    line = models.ForeignKey(Line, on_delete=models.CASCADE, related_name="stations")

    def __str__(self):
        return f"{self.name}"

class TimeSlot(models.Model):
    station = models.ForeignKey (Station, on_delete=models.CASCADE, related_name="station")
    time = models.TimeField()
    actual_time = models.TimeField(blank=True, null=True)
    
    def __str__(self):
        return f"{self.time}|{self.station}"
        
    class Meta:
        ordering = ('time',)

class Path(models.Model):
    cells = models.ManyToManyField(TimeSlot, related_name='cells')
    source = models.ForeignKey(Station, on_delete=True, related_name='path_source', null=True, blank=True)
    destination = models.ForeignKey(Station, on_delete=False, related_name='path_destination', null=True, blank=True)

    def __str__(self):
        x = ''
        for i in self.cells.all ():
            x += f"{i}" + "  "
        return x

class Train (models.Model):
    name = models.CharField(max_length=256)
    path = models.OneToOneField(Path, on_delete=models.CASCADE, related_name='path')
    
    def __str__(self):
        return f"{self.name}"



class UserFeedback(models.Model):
    trainid = models.IntegerField()
    time = models.TimeField()
    user_station = models.ForeignKey(Station, on_delete=models.CASCADE, related_name='user_station', null=True)
    
    def __str__(self):
        return f"{self.trainid}"
        
# Signals
