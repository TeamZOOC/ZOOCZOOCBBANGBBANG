# ZOOC
가족이 기록한 반려동물과의 순간들을 쭉- 추억하는 서비스, ZOOC<br/><br/>

### CONTRIBUTORS
* [권용민](https://github.com/briandr97)<br/>
* [김지영](https://github.com/Cat-JiYoung)<br/>
* [김지은](https://github.com/JIEUN-NA)<br/>
* [박진수](https://github.com/jinsu4755)<br/><br/>


* * *

# 1. Coding Convention
<br/>

## 1-1.안드로이드 스튜디오
* `Reformat Code` `(Ctrl + Alt + L)` 사용을 생활화한다<br><br>
  <br>

## 1-2. Kotlin Naming Convention
* `Camel Case`를 사용한다.<br><br>
#### 1-2-1. Class
* 클래스의 경우 `Upper Camel Case`를 사용한다.
```kotlin
// 클래스 예시
class MainActivity : AppCompatActivity()
class MainViewModel : ViewModel()
class HomeFragment : Fragment()
class HomeAdapter : RecyclerView.Adapter<HomeViewHolder>()
```
<br>

#### 1-2-2. Method, Variable
* 변수, 메소드의 경우 `Lower Camel Case`를 사용한다.
```kotlin
// 메소드 예시
private fun initList()
private fun clickBackArrow()
fun onBind() 
```
```kotlin
// 변수 예시
private lateinit var binding : ActivityMainBinding
private val adapter : MainAdapter by lazy { MainAdapter() }

private val _numbers = MutableLiveData<List<Int>>()
val numbers : LiveData<List<Int>> get() = _numbers
```
<br><br><br>

## 1-3. Resource Naming Convention
### 1-3-1. ID
`<WHAT>_<WHERE>_<DESCRIPTION>`

| View                 | Prefix |
| -------------------- | ------ |
| TextView             | `tv_`  |
| ImageView            | `iv_`  |
| EditText             | `et_`   |
| Button, ImageButton  | `btn_` |
| Toolbar              | `tb_`  |
| RecyclerView         | `rv_`  |
| Layout               |`layout_`|
| FragmentContainerView | `fcv_` |
| BottomNavigationView | `bnv_` |
| ..                   | ..     |

```xml
<!--예시-->
android:id="@+id/tv_home_title"
@+id/et_login_password
@+id/btn_onboarding_next
```
<br>

### 1-3-2. Drawable
`<WHAT_DESCRIPTION>`

| Drawable      | Prefix                                                      |
|---------------|-------------------------------------------------------------|
| Icon          | `ic_`                                                       |
| Image         | `img_`                                                      |
| Shape         | `<shape>_<radius>_<value>`                                  |
| Shape(border) | `<shape>_<bg color>_border_<border color>_<radius>_<value>` |

```
// 예시
ic_error.xml
img_default_user.xml
rect_radius_20.xml
rect_grey01_border_black01_radius_20.xml
```
<br>

### 1-3-3. Color
* Color의 이름은 '색상_hex code' 으로 정한다.
```xml
<!--예시-->
<color name="black_000000">#FF000000</color>
<color name="white_ffffff">#FFFFFFFF</color>
<color name="blue_6195ed">#6195ED</color>
```
<br>

### 1-3-4. String
* String 작성 시 주석을 통해 String이 사용되는 곳을 명시한다.
* 사용되는 곳과 설명으로 이름을 정한다.

`<WHERE/WHAT>_<DESCRIPTION>`
```xml
<!--예시-->

<!--Main Activity-->
<string name="main_next">다음</string>
<string name="main_title">멍이랑</string>

<!--Home Fragment-->
<string name="home_next">다음</string>
<string name="home_search">검색</string>
<string name="home_setting">설정</string>

<!--Dialog-->
<string name="msg_login">로그인하시겠습니까?</string>
<string name="msg_login_failed">로그인에 실패했습니다.</string>
<string name="msg_password_error">비밀번호가 올바르지 않습니다.</string>
```
<br><br>
* * *
<br><br>

# 2. Git Strategy
<br/>

* 기본적으로 Git Flow 전략을 이용한다. 작업 시작 시 선행되어야 할 작업은 다음과 같다.

```
1. Issue를 생성한다.
2. feature Branch를 생성한다.
3. Add - Commit - Push - Pull Request 의 과정을 거친다.
4. Pull Request가 작성되면 여유가 있으면 Code Review를 한다.
5. Code Review가 완료되면 Pull Request 작성자가 develop Branch로 merge 한다.
6. merge된 작업이 있을 경우, 다른 브랜치에서 작업을 진행 중이던 개발자는 본인의 브랜치로 merge된 작업을 Pull 받아온다.
7. 종료된 Issue와 Pull Request의 Label과 Project를 관리한다.
```
<br>

* 협업 시 준수해야 할 규칙은 다음과 같다.
```
1. develop에서의 작업은 원칙적으로 금지한다. 단, README 작성은 develop Branch에서 수행한다.
2. 자신이 담당한 부분 이외에 다른 팀원이 담당한 부분을 수정할 때에는 Slack을 통해 변경 사항을 전달한다.
3. 본인의 Pull Request는 본인이 Merge한다.
4. Commit, Push, Merge, Pull Request 등 모든 작업은 앱이 정상적으로 실행되는 지 확인 후 수행한다.
5. README 수정을 위한 Commit 도배는 금지한다. 미리보기는 Preview를 통해 확인한다.
```
<br>

## 커밋 메시지
* 커밋 메시지는 `Git Commit Template`을 이용하며 `Type of change`, `Scope of this change`, `Short Description`을 작성하고 자세히 작성할 경우 `Long description`까지 작성한다.
* `description`은 가능한 한글로 작성한다.
```
// 예시
prefix(scope): short description

long description
```
```
feat(signIn): 아이디 유효성 검사

 - 영어, 숫자 포함
 - 4~8 글자
```
<br>

## 브랜치 전략
* 브랜치는 main, develop, feat, hotfix로 나뉜다.
* 브랜치는 기능 단위로 생성하며 이는 이슈를 기반으로 한다. 단, 같은 범위의 기능이라면 같은 브랜치를 사용한다.
* 브랜치를 생성하기 전 이슈를 먼저 작성한다. 이슈를 작성 후 생성되는 번호와 간략한 설명을 조합하여 브랜치의 이름을 결정한다.
```
// 예시
main
develop/v1.0.0
feat/10-sign-up
feat/15-search
```
<br>

## 이슈
* 작업을 시작하기 전 이슈를 먼저 생성해야한다. 이슈는 작업 단위, 기능 단위로 생성하며 생성된 이슈 번호를 참조하여 브랜치명을 작성한다.
* 이슈 제목에는 기능의 대표적인 설명을 적고, 내용에는 세부적인 항목을 체크박스로 작성한다.
```
// 예시
회원가입 구현
- [ ] 아이디 입력받기
- [ ] 비밀번호 입력받기
```
<br>

## 풀 리퀘스트
* 풀 리퀘스트의 내용에는 변경된 사항(작업한 내용)에 대한 설명이 작성되어야한다.