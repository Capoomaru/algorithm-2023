# Chapter 1
## Sorting
### Comparable interface
### Selection Sort
### Insertion Sort
### Shell Sort
### Selection Sort
## 선형 정렬
키에 대한 추가적인 제약 조건(정보)을 가정하고 실행 \
ex) 키 값의 범위, 키의 기수(자리 수) 등
* Counting Sort(계수 정렬)
* Bucket Sort(버킷 정렬)
* Radix Sort(기수 정렬)
### Counting Sort(계수 정렬)
### Bucket Sort(버킷 정렬)
* bucket의 수가 2인 정렬 알고리즘은 quick sort
### Radix Sort(기수 정렬)
* 키의 자리수를 알 수 있을 때
* MSD sort(높은 자리 우선 정렬)
* LSD sort(낮은 자리 우선 정렬)
### Merge Sort(병합 정렬)
* 병합 방법
  * 별도의 배열 이용
  * 제자리 병합(In-place merge)
* 정렬 방법
  * Top-down Mergesort
    * 성능 개선
      1. 남은 부분의 크기가 특정 값보다 작을 경우 삽입 정렬 사용 \
        cutoff 값을 지정하여 해당 값을 비교함 \
        recursion 부담을 줄임 \
        해당 방법을 Java Collection API 에서 이용중
      2. 이미 정렬되어 있는 경우 병합하지 않기 \
         앞 부분의 제일 큰 원소 <= 뒷 부분의 제일 작은 원소 일 때
      3. a[]와 aux[]의 역할을 교대로 담당하자 \
         병합할 때 a[]를 aux[]에 복사하는 시간이 없어짐 \
         merge 호출이 많을 때 유리함
  * Bottom-up Mergesort
### External Sort(외부 정렬)
메모리의 한계로 인한 내부 정렬의 한계를 극복하고자 그룹(run) 단위로 쪼개어 정렬한 후 merge 한다
* Binary Sort/Merge
* Balanced Binary Sort/Merge
* Balanced K-way Sort/Merge
* Polyphase Sort/Merge
### 2 Phase Multiway Merge/Sort(2PMM)
### Huffman Tree