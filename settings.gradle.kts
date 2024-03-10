rootProject.name = "home-garden"

include("gateway")
include("service-discovery")
include("config-server")
include("room-service")
include("device-service")
include("device-command-service")
include("job-service")

include("platform:backup-service")
findProject(":platform:backup-service")?.name = "backup-service"
include("finance")
include("finance:fund-service")
findProject(":finance:fund-service")?.name = "fund-service"
