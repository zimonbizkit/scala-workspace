FROM hseeberger/scala-sbt
ENV SBT_OPTS -XX:+CMSClassUnloadingEnabled -Xmx4G
## installing ammonite shell
RUN sh -c '(echo "#!/usr/bin/env sh" && curl -L https://github.com/lihaoyi/Ammonite/releases/download/1.6.6/2.12-1.6.6) > /usr/local/bin/amm && chmod +x /usr/local/bin/amm'
