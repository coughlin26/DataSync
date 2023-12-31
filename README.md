# DataSync

Data persistence and synchronization project

## ASSIGNMENT

Based on your understanding of the product today, design a data model to aid in local persistence
for member daily breath checks. Using your favorite persistence framework, create a bidirectional
sync service (upload & fetch) for both online and offline support. Feel free to mock the network
endpoints.
The question is purposefully not detailed so liberties can be taken based on their thoughts. If
there are questions however please let the assigner know. A lot can be learned from the how-to
material found on our website.

## GOALS

1. Define the data model for a daily breath check.
2. Mock a backend to respond and save entries.
3. Create a local persistent storage space for offline use.
4. Allow the local data to synchronize with the remote data.

## QUESTIONS

1. How should duplicates and collisions be handled?
2. Can a user edit an entry?
3. What happens if a user edits an existing entry while offline, and someone else edits the same
   entry? Once a connection is reestablished, what edit is preferred?

## ASSUMPTIONS

1. Duplicates and collisions will be overwritten based on timestamp (newest chosen)
2. Yes, editing is allowed.
3. Unclear, more research and testing is required.

## CURRENT ISSUES

1. The remote API does not use or consider accounts. Currently that means this only works for 1
   user.
2. There is no way to add remote breath checks to test the API sync.

## FUTURE WORK

1. Define more interfaces in the libraries and allow consumers to create their own implementation.
2. Build out an account based sync system.
3. Implement a real `SyncAdapter` to handle the API
   sync. https://developer.android.com/training/sync-adapters/creating-sync-adapter#CreateSyncAdapter
4. Limit API and database calls by timestamp where appropriate to reduce the number of transactions,
   data transferred, and time to execute.
