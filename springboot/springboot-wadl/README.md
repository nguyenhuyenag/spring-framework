# boot-all

Try searching for redis folder on your machine, in my case, I had redis-server running in another application. Switch to that directory and run:

> redis-cli ping

If it returns PONG, run:

> redis-cli shutdown

This should shut down the redis, and now go to your project and try running:

> redis-server
