rootProject.name = "home-garden"

include("gateway")
include("service-discovery")
include("config-server")
include("room-service")
include("device-service")
include("device-command-service")
include("job-service")
include("ui")

//PLATFORM: backup-service
include("platform:backup-service")
findProject(":platform:backup-service")?.name = "backup-service"

//PLATFORM: dictionary-service
include("platform:dictionary-service:dictionary-service-impl")
findProject(":platform:dictionary-service:dictionary-service-impl")?.name = "dictionary-service-impl"

include("platform:dictionary-service:dictionary-service-dto")
findProject(":platform:dictionary-service:dictionary-service-dto")?.name = "dictionary-service-dto"

include("platform:dictionary-service:dictionary-service-client")
findProject(":platform:dictionary-service:dictionary-service-client")?.name = "dictionary-service-client"

//PLATFORM: user-profile-service
include("platform:user-profile-service:user-profile-service-graphql")
findProject(":platform:user-profile-service:user-profile-service-graphql")?.name = "user-profile-service-graphql"

include("platform:user-profile-service:user-profile-service-dto")
findProject(":platform:user-profile-service:user-profile-service-dto")?.name = "user-profile-service-dto"

include("platform:user-profile-service:user-profile-service-client")
findProject(":platform:user-profile-service:user-profile-service-client")?.name = "user-profile-service-client"

//PLATFORM: session-service
include("platform:session-service:session-service-graphql")
findProject(":platform:session-service:session-service-graphql")?.name = "session-service-graphql"

include("platform:session-service:session-service-dto")
findProject(":platform:session-service:session-service-dto")?.name = "session-service-dto"

include("platform:session-service:session-service-client")
findProject(":platform:session-service:session-service-client")?.name = "session-service-client"

//fund-service
include("finance:fund-service:fund-service-client")
findProject(":finance:fund-service:fund-service-client")?.name = "fund-service-client"

include("finance:fund-service:fund-service-dto")
findProject(":finance:fund-service:fund-service-dto")?.name = "fund-service-dto"

include("finance:fund-service:fund-service-impl")
findProject(":finance:fund-service:fund-service-impl")?.name = "fund-service-impl"

//product-service
include("finance:product-service")
findProject(":finance:product-service")?.name = "product-service"

//history-service
include("finance:history-service:history-service-impl")
findProject(":finance:history-service:history-service-impl")?.name = "history-service-impl"

include("finance:history-service:history-service-dto")
findProject(":finance:history-service:history-service-dto")?.name = "history-service-dto"

include("finance:history-service:history-service-client")
findProject(":finance:history-service:history-service-client")?.name = "history-service-client"

//rate-service
include("finance:rate-service")
findProject(":finance:rate-service")?.name = "rate-service"

include("finance:rate-service:rate-service-graphql")
findProject(":finance:rate-service:rate-service-graphql")?.name = "rate-service-graphql"

include("finance:rate-service:rate-service-dto")
findProject(":finance:rate-service:rate-service-dto")?.name = "rate-service-dto"

include("finance:rate-service:rate-service-procedure")
findProject(":finance:rate-service:rate-service-procedure")?.name = "rate-service-procedure"

include("finance:rate-service:rate-service-dbm")
findProject(":finance:rate-service:rate-service-dbm")?.name = "rate-service-dbm"

//extra extension
include("module:eureka-client-extension")
findProject(":module:eureka-client-extension")?.name = "eureka-client-extension"

include("module:graphql-openfeigh-extension")
findProject(":module:graphql-openfeigh-extension")?.name = "graphql-openfeigh-extension"

include("module:graphql-client-extension")
findProject(":module:graphql-client-extension")?.name = "graphql-client-extension"
