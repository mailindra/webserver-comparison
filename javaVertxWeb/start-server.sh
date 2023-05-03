java \
    -server                                           \
    -XX:+UseNUMA                                      \
    -XX:+UseParallelGC                                \
    -Dvertx.disableMetrics=true                       \
    -Dvertx.disableH2c=true                           \
    -Dvertx.disableWebsockets=true                    \
    -Dvertx.flashPolicyHandler=false                  \
    -Dvertx.threadChecks=false                        \
    -Dvertx.disableContextTimings=true                \
    -Dvertx.disableTCCL=true                          \
    -Dvertx.disableHttpHeadersValidation=true         \
    -Dvertx.eventLoopPoolSize=$((`grep --count ^processor /proc/cpuinfo`)) \
    -Dio.netty.buffer.checkBounds=false               \
    -Dio.netty.buffer.checkAccessible=false           \
    -jar                                              \
    target/vertx_web-1.0-SNAPSHOT-fat.jar          \
    --instances                                       \
    `grep --count ^processor /proc/cpuinfo`           \
