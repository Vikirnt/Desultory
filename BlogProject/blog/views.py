from django.shortcuts import render, redirect
from .models import Post
from .forms import PostForm

def thread_list (request):
    threads = Post.objects.all ()
    return render (request, 'main/threadlist.html', {'threads': threads})

def thread_max (request, pid):
    x = Post.objects.get (id=pid)
    return render (request, 'main/threadmax.html', {'post': x, 'user': request.user})

def add_post (request):
    if request.method == "POST":
        form = PostForm (request.POST)
        if form.is_valid ():
            post = form.save (commit=False)
            post.save ()
            return redirect ('/')

    form = PostForm ()
    return render (request, 'main/addpost.html', {'form': form})
