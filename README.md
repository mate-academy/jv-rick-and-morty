**Rick And Morty API**

**Description:**
This project implements a RESTful API for retrieving information about characters from the animated series "Rick & Morty". The API provides two main methods: one for generating random character information and another for searching characters by name.

**Business Requirements:**
1. **Random Character Generation:**
   - Endpoint: `/api/character/random`
   - Method: GET
   - Description: Returns information about a randomly selected character from the "Rick & Morty" universe.
   - Response Example:
     ```json
     {
       "id": 1,
       "externalId": "1",
       "name": "Rick Sanchez",
       "status": "Alive",
       "gender": "Male"
     }
     ```
   - Note: The `externalId` field corresponds to the original character ID obtained from the external API, while the `id` field represents the identifier of the character within the internal database.

2. **Character Search:**
   - Endpoint: `/api/character/search`
   - Method: GET
   - Description: Retrieves a list of characters whose name contains the specified search string.
   - Query Parameter:
     - `name` (string): The search string for character names.
   - Note: The application fetches data from a third-party service and stores it in the internal database during startup. All API requests operate on this local database.
