# Enhanced Artifact (Algorithms & Data Structures)

## What this is
This is an enhanced version of my CS-320 "Contact Service" artifact, reused in CS-499 to demonstrate
improvement in the **Algorithms and Data Structures** category.

## What changed (the enhancement)
### Baseline limitations
The baseline service (see `../original_baseline`) uses an `ArrayList` and linear searches for add, delete, update, and lookup.
That means common operations are **O(n)**.

### Enhancements
1. **O(1) average lookup/update/delete by contact ID**
   - Replaced the linear list with `HashMap<String, Contact>` (`byId`).
2. **Added an indexed search feature using a balanced tree**
   - Added a `TreeMap<String, Set<String>>` (`lastNameIndex`) to support **prefix searching** on last names.
   - Prefix range lookup is `O(log n)` to find the start of the range plus `O(k)` for the matches.
3. **Stronger consistency + test coverage**
   - Updating last name now re-indexes safely (remove old key, add new key).
   - Added tests verifying indexing behavior and validation.

## How to run tests
```bash
mvn test
```
