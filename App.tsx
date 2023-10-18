/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React, { useEffect } from 'react';
import type {PropsWithChildren} from 'react';
import {
  Button,
  NativeModules,
  NativeEventEmitter,
  Text,
  View
} from 'react-native';

const {LivenessModule} = NativeModules;
console.log(LivenessModule);
LivenessModule.fun(res => console.log(res));

const eventEmitter = new NativeEventEmitter(LivenessModule);

function App(): JSX.Element {
  
  const funPromise=async()=>{
    try{
      var result=await LivenessModule.funPromise();
      console.log(result);
    } catch(e){
      console.log(e);
    }
  }

  const livenessViewFun=async()=>{
    try{
      await LivenessModule.livenessViewFun();
    } catch(e){
      console.log(e);
    }
  }

  useEffect(()=>{
    eventEmitter.addListener('funEmitter', res=>{
      console.log(res);
    })
    return()=>{
      eventEmitter.removeAllListeners('funEmitter');
    }
  })

  return (
    <View>
      <Text>Yoooooooooooooooooo</Text>
      <Button title="promise button" onPress={funPromise} />
      <Button title="liveness view" onPress={livenessViewFun}  />
    </View>
  );
}

export default App;
