import { Button, Typography, useScrollTrigger } from '@mui/material'
import React, { use, useContext, useEffect, useState } from 'react'
import { AuthContext } from 'react-oauth2-code-pkce';
import { useDispatch } from 'react-redux';
import { setCredentials } from '../store/authSlice';
import { Navigate } from 'react-router-dom';
const starter = () => {
  const { token, tokenData, logIn, logOut, isAuthenticated } = useContext(AuthContext);
  const dispatch = useDispatch();
  const [authIsReady, setAuthIsReady] = useState(false);

  useEffect(() => {
    if(token){
      dispatch(setCredentials({token, user: tokenData}))
      setAuthIsReady(true);
    }
  }, [token, tokenData, dispatch])
  return (
    <>
      {!token ? 
        (
          <>
            <div className="h-screen w-screen flex items-center justify-center bg-gray-50">
      <div className="text-center">
        <h1 className="text-5xl font-bold text-gray-800">
          Welcome to Fitness Tracker
        </h1>

        <p className="text-lg text-gray-600 mt-4 mb-8">
          Login to continue and manage your fitness activities.
        </p>

        <button
          onClick={() => {logIn()}}
          className="px-12 py-3 bg-blue-600 hover:bg-blue-700 text-white rounded-xl text-lg font-medium transition"
        >
          Login
        </button>
      </div>
    </div>
          </>
        ) : 
        (
          <Navigate to="/activityForm" ></Navigate>
        )
      }
    
    </>
  )
}

export default starter