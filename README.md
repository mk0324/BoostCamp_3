# 부스트캠프 Android 사전과제 - 영화정보 검색 어플리케이션
- MyMovieApp

# 빌드 고려사항 
현재 지원자의 컴퓨터에서는
gradle.properties 파일의
android.enableAapt2=false 부분이 있어야 빌드가 됨

# 구현
## 필수 구현사항
- EditText를 통해 검색어를 입력받아 `검색`버튼으로 영화 검색
- [네이버 검색 API](https://developers.naver.com/docs/search/movie/)를 활용하여 검색어에 해당하는 결과 받아오기
- 검색결과를 RecyclerView에 표시하기
- 각 영화정보에는 아래 정보가 모두 표시
: 썸네일 이미지, 제목, 평점, 연도, 감독, 출연배우
- 목록에서 영화 선택시 해당 영화 정보 link페이지로 이동

## 선택 구현사항
### 구조 및 라이브러리
- MVP 패턴 사용

### 기능 구현
- 검색결과 무한 스크롤 구현
- 유효하지 않은 검색어, 검색결과 처리
- 기타 알수 없는 에러발생 처리
