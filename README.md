# Assignment 1 - Report: Divide and Conquer & Asymptotic Notations

## 1. Asymptotic Bounds Table

| Algorithm | Best Case | Average Case | Worst Case | Reason / Input Cause |
| :--- | :--- | :--- | :--- | :--- |
| **MergeSort** | $\Theta(n \log n)$ | $\Theta(n \log n)$ | $\Theta(n \log n)$ | Always splits array in half and merges linearly. |
| **QuickSort** | $\Theta(n \log n)$ | $\Theta(n \log n)$ | $\Theta(n \log n)$ | Random pivot + 3-way partition handles duplicates and avoids $O(n^2)$. |
| **QuickSelect** | $\Theta(n)$ | $\Theta(n)$ | $\Theta(n^2)$ | Worst case occurs with malicious pivots (mitigated by randomization). |
| **Insertion Sort** (cutoff $\le 15$) | $\Theta(n)$ | $\Theta(n^2)$ | $\Theta(n^2)$ | Used for small subarrays; best case is already sorted data. |

---

## 2. Recurrence Relations & Master Theorem

### MergeSort
* **Recurrence:** $T(n) = 2T(n/2) + \Theta(n)$
* **Parameters:** $a = 2, b = 2, f(n) = O(n^1)$
* **Master Theorem Case:** Case 2 ($f(n) = \Theta(n^{\log_b a}) = \Theta(n^1)$).
* **Result:** $T(n) = \Theta(n \log n)$.

### QuickSort (Balanced Split Assumption)
* **Recurrence:** $T(n) = 2T(n/2) + \Theta(n)$
* **Master Theorem Case:** Case 2 $\rightarrow T(n) = \Theta(n \log n)$.
* **Explanation for Random Pivot:** Choosing a random pivot ensures that extreme splits are rare. On average, the split is reasonably balanced, preventing the recurrence from degenerating into $O(n^2)$. Combined with the 3-way partition, identical elements are grouped instantly.

### QuickSelect (Average Case)
* **Recurrence:** $T(n) = T(n/2) + \Theta(n)$
* **Master Theorem Case:** Case 3 (since $f(n) = \Theta(n)$ dominates $a T(n/b)$).
* **Result:** $T(n) = \Theta(n)$ on average.

---

## 3. Empirical Analysis & Discussion
* **Do measurements match theory?** Yes, the execution times for MergeSort and QuickSort closely follow the $O(n \log n)$ growth curve. 
* **Differences & Anomalies:** Minor deviations for smaller $n$ ($1,000$) are caused by JVM warm-up effects, Just-In-Time (JIT) compilation optimizations, and CPU cache locality. 
* **Cutoff & Buffer Impact:** Using a single reusable buffer in MergeSort eliminates memory churn (garbage collection pauses), and the Insertion Sort cutoff ($\le 15$) significantly reduces recursive overhead for small sub-arrays.

---

## 4. Benchmark Visualizations
![Time vs N] <img width="1000" height="600" alt="time_vs_n" src="https://github.com/user-attachments/assets/0ff141c7-dd56-4e62-9aa7-9eb3096fd8ad" />
![Max Recursion Depth vs N] <img width="1000" height="600" alt="depth_vs_n" src="https://github.com/user-attachments/assets/13bee816-9328-47cd-b874-fac754c2b51e" />

![Ratio Bound Check] <img width="1000" height="600" alt="ratio_vs_n" src="https://github.com/user-attachments/assets/bf379f90-87bb-4bb7-948b-489bc351e459" />
