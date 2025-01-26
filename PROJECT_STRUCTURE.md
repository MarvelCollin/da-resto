# DaResto Project Structure

## Core Components
- `src/models/`
  - Entity/
    - Chef.java - Chef entity with state pattern
    - Waiter.java - Waiter entity with state pattern
    - Customer.java - Customer entity with state pattern
  - States/
    - ChefState/
    - WaiterState/
    - CustomerState/
  - Factory/
    - RestaurantFactory.java - Singleton factory pattern
  
- `src/controllers/`
  - Facades/
    - GameFacade.java - Singleton facade pattern
  - Mediators/
    - RestaurantMediator.java - Mediator pattern

## Design Patterns Used
- Singleton: GameFacade, RestaurantFactory
- State: All entities (Chef, Waiter, Customer)
- Factory: Entity creation
- Mediator: Entity communication
- Observer: Customer generation
- Facade: Game commands
