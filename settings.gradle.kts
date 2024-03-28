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

include("finance:fund-service")
findProject(":finance:fund-service")?.name = "fund-service"

include("finance:product-service")
findProject(":finance:product-service")?.name = "product-service"

include("finance:history-service")
findProject(":finance:history-service")?.name = "history-service"

include("module:eureka-client-extension")
findProject(":module:eureka-client-extension")?.name = "eureka-client-extension"

include("module:graphql-openfeigh-extension")
findProject(":module:graphql-openfeigh-extension")?.name = "graphql-openfeigh-extension"
