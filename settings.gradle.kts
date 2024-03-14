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

include("module:eureka-client-extension")
findProject(":module:eureka-client-extension")?.name = "eureka-client-extension"
