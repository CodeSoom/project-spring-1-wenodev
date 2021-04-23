import {useState} from 'react';
import * as API from '../services/api';

export default function SignUpForm(){
    const [user, setUser] = useState({
        email : null,
        password : null,
        name : null,
    });


    function onChange(event){
        const{name, value} = event.target;
        setUser({
          ...user,
          [name]: value
        })
    }

    function submitUser(){
    }
    
    return(
    <div>
        <label>이메일</label>
        <input 
            type="text" 
            name="email"
            placeholder="이메일을 입력하세요"
            value={user.email} 
            onChange={onChange} 
        />
        <label>비밀번호</label>
        <input 
            type="password" 
            name="password"
            placeholder="비밀번호를 입력하세요"
            value={user.password} 
            onChange={onChange}
        />        
        <label>이름</label>
        <input 
            type="text" 
            name="name"
            placeholder="이름을 입력하세요"
            value={user.name} 
            onChange={onChange}
        />       
        <button type="submit" onClick={submitUser}>가입하기</button>
        </div>
        
    )

}