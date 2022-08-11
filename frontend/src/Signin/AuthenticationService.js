import axios from "axios";

export function executeJwtAuthentication(email, password) {
    return axios.post("/api/user/login", {
        mailAddress: email,
        password: password,
    });
}

export function registerSuccessfulLoginForJwt(token) {
    sessionStorage.setItem("token", token);
}
