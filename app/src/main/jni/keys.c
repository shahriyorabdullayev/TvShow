#include <jni.h>
JNIEXPORT jstring JNICALL
Java_com_shahriyor_android_1imperative_data_remote_Server_getServerDevelopment(JNIEnv *env,jobject thiz) {
    return (*env) -> NewStringUTF(env, "https://www.episodate.com/");
}

JNIEXPORT jstring JNICALL
Java_com_shahriyor_android_1imperative_data_remote_Server_getServerProduction(JNIEnv *env,jobject thiz) {
    return (*env) -> NewStringUTF(env, "https://www.episodate.com/");
}