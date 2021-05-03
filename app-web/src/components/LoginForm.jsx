import {useState} from 'react'

export default function LoginForm(){
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    return(
    <div>
        <h2>로그인</h2>
        <label>이메일</label>
        <input type="text" value={email} placeholder="이메일을 입력하세요" />
        <label>비밀번호</label>
        <input type="password" value={password} placeholder="비밀번호를 입력하세요" />
    </div>)
}