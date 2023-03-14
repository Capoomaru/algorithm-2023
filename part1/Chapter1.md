# Chapter 1 : Sorting
## Comparable interface
## 키값 비교에 의한 정렬
* 선택정렬
* 삽입정렬
* 쉘 정렬
* etc
### Selection Sort(선택 정렬)
리스트 내의 정렬되지 않은 부분 전체에서부터 최소값을 찾아서 찾는 자리수의 값과 교환
#### 성능
* 비교 연산 : ~ $N^2/2$
* 교환 연산 : ~ $N$
#### 특징
* 입력 데이터의 상태와 무관(일부 정렬/완전 비정렬)
* 데이터 이동이 최소화 -> 데이터 이동의 비용이 큰 시스템이 용이
* 제자리 정렬 -> 메모리를 추가로 사용하지 않음
* not stable -> 같은 값이 있을 때 기존 순서가 지켜지지 않음

---
#### ✔︎ assert란?
assert는 jdk 1.4부터 지원되는 단언문으로 객체가 아닌 예약어이다. \
아래의 코드와 같이 assert 뒤의 결과값으로 false가 반환되면 AssertionError 예외(Exception)가 발생한다.\
디버깅 용도로 활용되므로 동작을 위해서 -ea 옵션을 붙여서 컴파일하여야한다.
```java
assert isSorted(list);
```
참고문헌 : [블로그 - offbyone.tistory.com](https://offbyone.tistory.com/294)

### Insertion Sort
리스트 내부의 정렬된 리스트를 만들고 다음 값을 정렬된 리스트에 주입함

#### 성능
* 최악
  * 비교 연산(최악) : ~ $N^2/2$
  * 교환 연산(최악) : ~ $N^2/2$
* 최선
  * 비교 연산(최선) : ~ $N$
  * 교환 연산(최선) : 0

#### 특징
* 이미 정렬된 리스트를 다시 정렬하는 경우 교환 연산이 이루어지지 않음
* 단, 비교연산과 교환연산의 비용이 높은 편

### Shell Sort
기존 삽입 정렬의 개선 버전으로 h 만큼 떨어진 원소끼리 정렬 리스트를 유지하여 h = 1 일 때까지 정렬하여 정렬하는 방법 \
~~정렬 과정 시험 빈번하게 출제됨~~

#### 특징
* 기존 Insertion Sort(삽입 정렬)의 경우 작은 값이 뒤에 있을 경우 교환 횟수가 많음
* 이를 개선하기 위해 h 만큼 이동시켜서 교환 횟수를 줄이는 방식
  * h-sorted array : h 만큼 떨어진 원소들끼리의 정렬된 리스트를 부르는 호칭
  * h = 1 인 경우, 일반 삽입 정렬과 동일
* h 값이 클수록 리스트의 길이가 짧다.(정렬 초기 상태)
* h 값이 작을수록 리스트의 길이가 길다.(정렬 마무리 상태)

#### h 할당 규칙
* h = 1 일 때까지 진행하므로 마지막 단계에서는 무조건 정렬이 됨
* 수열이 길면 단계 수가 증가하고, 수열이 짧으면 성능 향상 정도가 미미
* 최선의 수열은 아직 발견되지 않음
  * 일반적으로 $h_i = 3 * h_{i-1} + 1$ 수열을 많이 사용함
    * 복잡도 : $O(N^1.5)$ => $O(N\sqrt{N})$

## 선형 정렬
키에 대한 추가적인 제약 조건(정보)을 가정하고 실행 \
ex) 키 값의 범위, 키의 기수(자리 수) 등
* Counting Sort(계수 정렬)
* Bucket Sort(버킷 정렬)
* Radix Sort(기수 정렬)
### Counting Sort(계수 정렬)
각 키의 빈도수를 계산하여 각 key의 등장 index를 가지는 배열을 통해 정렬을 진행
#### 조건
* 0 <= key <= k - 1, key는 정수

#### 특징
* 3개의 배열을 필요로함
  * A[n] : 입력된 리스트
  * C[k] : 각 키의 빈도수 및 인덱스 저장 리스트
  * B[n] : 정렬된 리스트
* 각 키의 인덱스를 저장할 때와 정렬된 리스트를 계산할 때 서로 역순으로 리스트 내 원소를 참조함
  * Stable을 유지하기 위해서

#### 성능
* 공간 복잡도 : O(N+K)
* 시간 복잡도 : O(N+K)
  * N과 K의 규모가 비슷하면 O(N) 만에 해결 가능
  * 하지만, K= $N^2$ 일 경우 $O(N^3)$이 되는 리스크 존재
  * 즉, 키의 범위가 좁은 경우 유리함


### Bucket Sort(버킷 정렬)
* bucket의 수가 1일 경우 counting sort(계수정렬)
* 각 버킷을 정렬하는 별도 단계가 필요함.(기수정렬)
  * bucket의 수가 2인 정렬 알고리즘은 quick sort

#### 동작과정
* 초기에 비어있는 버킷 준비
* 분할 단계 : 입력 배열의 원소들을 해당되는 버킷에 저장
* 정렬 단계 : 각 버킷을 정렬
* 수집 단계 : 버킷을 차례로 방문하여 원소들을 원 배열에 저장

### Radix Sort(기수 정렬)
* 키의 자리수를 알 수 있을 때
* MSD sort(높은 자리 우선 정렬)
  * 문자열 정렬
  * 정렬 방법
    * K1 기준으로 버킷 정렬
    * 각 버킷에 대해 K2 기준으로 다시 버킷 정렬
    * 이후 버킷을 순서대로 합침
* LSD sort(낮은 자리 우선 정렬)
  * 버킷별로 별도 정렬 단계가 필요없어 MSD 보다 많이 사용됨
  * 정렬 방법
    * K2 기준으로 버킷 정렬
    * 각 버킷들을 합침
    * 다시 K1 기준으로 버킷 정렬(**stable sorting 알고리즘을 사용해야함 -> 보통 계수정렬 사용**)
### Merge Sort(병합 정렬)
정렬된 두 개의 서브 리스트를 병합하는 방법 
#### 특징
* java API에서 현재 사용 중인 정렬
* 이후 외부 정렬에서도 활용되는 알고리즘

* 병합 방법
  * 별도의 배열 이용
  * 제자리 병합(In-place merge)
* 정렬 방법
  * Top-down Mergesort
    * 성능
      * 길이가 N인 배열의 경우, 비교 횟수 : C(N)
      * $C(N)$ = $O(N*log_{2}{N})$
      * 공간 복잡도 : O(N)
      * 성능 개선
        1. **남은 부분의 크기가 특정 값보다 작을 경우 삽입 정렬 사용** \
          cutoff 값을 지정하여 해당 값을 비교함 \
          recursion 부담을 줄임 \
          해당 방법을 <U>Java Collection API</U> 에서 이용 중
            ``` java
            private static void sort(Comparable[] list, Comparable[] aux, int lo, int hi) {
                if (hi <= lo) return;
                if (hi <= lo + CUTOFF - 1) { // CUTOFF로 설정된 개수보다 적으면
                  Insertion.sort(a, lo, hi); // stable한 정렬인 삽입정렬 사용
                  return;
                }
                int mid = lo + (hi - lo) / 2; 
                sort(list, aux, lo, mid);       
                sort(list, aux, mid + 1, hi);
                merge(list, aux, lo, mid, hi);  
             }
            ```
          
        2. **이미 정렬되어 있는 경우 병합하지 않기** \
           앞 부분의 제일 큰 원소 <= 뒷 부분의 제일 작은 원소 일 때 \
           비교 연산이 추가되어 손해라고 생각할 수 있지만, 투자 대비 이익이 큰 편
           ```java
           private static void sort(Comparable[] list, Comparable[] aux, int lo, int hi) {
              if (hi <= lo) return;
              int mid = lo + (hi - lo) / 2; 
              sort(list, aux, lo, mid);       
              sort(list, aux, mid + 1, hi);   
              if(less(list[mid], list[mid + 1]))  //비교 구문 추가
                  return;
              merge(list, aux, lo, mid, hi);  
           }
           ```

        3. **a[]와 aux[]의 역할을 교대로 담당하자** \
           병합할 때 a[]를 aux[]에 복사하는 시간이 없어짐 \
           <U>merge 호출이 많을 때 유리함</U>
           ```java
           private static void sort(Comparable[] list, Comparable[] aux, int lo, int hi) {
              if (hi <= lo) return;
              int mid = lo + (hi - lo) / 2; 
              sort(aux, list, lo, mid);       // aux, list 순서 변경
              sort(aux, list, mid + 1, hi);   // aux, list 순서 변경
              merge(aux, list, lo, mid, hi);  // aux, list 순서 변경
           }
           ```
  * Bottom-up Mergesort
### External Sort(외부 정렬)
메모리의 한계로 인한 내부 정렬의 한계를 극복하고자 그룹(run) 단위로 쪼개어 정렬한 후 merge 한다
* Binary Sort/Merge
* Balanced Binary Sort/Merge
* Balanced K-way Sort/Merge
* Polyphase Sort/Merge
### 2 Phase Multiway Merge/Sort(2PMM)
### Huffman Tree
