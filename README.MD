# IoT Data Manager (MongoDB)

The IoT Data Manager aims to provide a reactive REST API to handle database operations for managing IoT devices for a
cloud service provider. Ideally, the database layer will be extracted into a DAL (Data Access Layer) library. However,
this project is just the starting point, and the DAL is still under development and review.

## Database models

### Customer

Represents either an organization or an individual that owns the devices. A customer can have sub-customers, each with
one or more admins.

### User

Refers to the individual who uses and can control the devices. A user can have roles such as admin, customer-admin,
guest, or a regular user.

### Area

Denotes a physical space containing devices. This could be a room, floor, building, car, etc.

### Device

Represents a tangible device that can be controlled, such as a light, switch, sensor, etc.

## Technical Goals

- Develop using Java Spring WebFlux and reactive database repositories.
- Test the Reactor project along with the utilization of Mono and Flux.
- Experiment with MongoDB.
- Refresh knowledge on Java Spring using Kotlin.
- Incorporate an aspect using Java Spring AOP.





