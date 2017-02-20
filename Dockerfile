FROM jenac/oracle-jdk8:121

ADD docker_app /app
WORKDIR /app
CMD ["/app/start.sh"]
