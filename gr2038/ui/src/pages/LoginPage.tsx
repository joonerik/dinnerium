import React, { useState } from "react"

const LoginPage = () => {
  const [isUser, setUser] = useState<string>("")

  const getUser = async (user: string) => {
    console.log(user)
    const response = await fetch(`/users/${user}`, {
      mode: "no-cors",
    })
    const data = await response.json()
    console.log(data)
  }
  return (
    <div className="login__modal">
      <h2> Your Username</h2>
      <input
        type="text"
        name="username"
        id="usernameInput"
        required
        placeholder="Your username"
        onChange={(event) => setUser(event.target.value)}
      />

      <button type="submit" onClick={() => getUser(isUser)}>
        Login
      </button>
    </div>
  )
}
export default LoginPage
