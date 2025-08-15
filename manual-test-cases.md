# Manual Test Cases for BST Visualizer

## 1. Enter Numbers Page
- **URL:** `/enter-numbers`
- **Method:** GET
- **Steps:**
    1. Open browser, go to `http://localhost:8080/enter-numbers`
    2. Enter `7, 3, 9, 1, 5`
    3. Click "Create Tree Snapshot"
- **Expected:** Redirects to tree snapshot view showing input and generated JSON.

## 2. Process Numbers Endpoint
- **URL:** `/process-numbers`
- **Method:** POST
- **Steps:**
    1. Submit form with `numbers=10, 5, 15`
- **Expected:** Tree JSON structure displayed on page.

## 3. View Previous Snapshots
- **URL:** `/previous-snapshots`
- **Method:** GET
- **Steps:**
    1. Visit URL after adding snapshots.
- **Expected:** Table of past snapshots with date and tree JSON.

