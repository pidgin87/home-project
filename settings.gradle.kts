rootProject.name = "home-garden"

include("gateway")
include("service-discovery")
include("config-server")
include("room-service")
include("device-service")
include("device-command-service")
include("job-service")
include("ui")

include("platform:backup-service")
findProject(":platform:backup-service")?.name = "backup-service"

include("platform:dictionary-service:dictionary-service-impl")
findProject(":platform:dictionary-service:dictionary-service-impl")?.name = "dictionary-service-impl"

include("platform:dictionary-service:dictionary-service-dto")
findProject(":platform:dictionary-service:dictionary-service-dto")?.name = "dictionary-service-dto"

include("platform:dictionary-service:dictionary-service-client")
findProject(":platform:dictionary-service:dictionary-service-client")?.name = "dictionary-service-client"

include("finance:fund-service")
findProject(":finance:fund-service")?.name = "fund-service"

include("finance:product-service")
findProject(":finance:product-service")?.name = "product-service"



include("finance:history-service:history-service-impl")
findProject(":finance:history-service:history-service-impl")?.name = "history-service-impl"

include("finance:history-service:history-service-dto")
findProject(":finance:history-service:history-service-dto")?.name = "history-service-dto"

include("finance:history-service:history-service-client")
findProject(":finance:history-service:history-service-client")?.name = "history-service-client"



include("module:eureka-client-extension")
findProject(":module:eureka-client-extension")?.name = "eureka-client-extension"

include("module:graphql-openfeigh-extension")
findProject(":module:graphql-openfeigh-extension")?.name = "graphql-openfeigh-extension"
