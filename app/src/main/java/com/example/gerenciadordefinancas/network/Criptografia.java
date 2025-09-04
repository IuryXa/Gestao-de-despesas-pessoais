package com.example.gerenciadordefinancas.network;

public class Criptografia {

    public static String Criptografar(String dado, String chave) {
        char[] dadoSplit = dado.toCharArray();
        char[] chaveSplit = chave.toCharArray();
        int[] keyCodes = new int[chaveSplit.length];
        int keyCodeDado;
        char dadoCriptChar;
        StringBuilder dadoCriptografado = new StringBuilder();

        for(int i = 0; i<chaveSplit.length; i++) {
            keyCodes[i] = chaveSplit[i];
        }
        for(int i=0, j=0;i<dadoSplit.length;i++) {
            keyCodeDado = dadoSplit[i];
            keyCodeDado += keyCodes[j];
            j++;
            if(j > keyCodes.length -1) {
                j = 0;
            }
            keyCodeDado += keyCodes[j];
            if(keyCodeDado > 255) {
                keyCodeDado -=223;
            }
            dadoCriptChar = (char) keyCodeDado;
            dadoCriptografado.append(dadoCriptChar);
        }
        return dadoCriptografado.toString();
    }

    public static String Descriptografar(String dado, String chave) {
        char[] dadoCriptoSplit = dado.toCharArray();
        char[] chaveSplit = chave.toCharArray();
        int[] keyCodes = new int[chaveSplit.length];
        int keyCodeDadoCript;
        char dadoDescriptChar;
        StringBuilder dadoDescriptografado = new StringBuilder();

        for(int i = 0; i<chaveSplit.length; i++) {
            keyCodes[i] = chaveSplit[i];
        }

        for(int i=0,j=0;i<dadoCriptoSplit.length;i++) {
            keyCodeDadoCript = dadoCriptoSplit[i];
            keyCodeDadoCript -= keyCodes[j];
            j++;
            if(j > keyCodes.length -1) {
                j=0;
            }
            keyCodeDadoCript -=keyCodes[j];
            if(keyCodeDadoCript < 32) {
                keyCodeDadoCript +=223;
            }
            dadoDescriptChar = (char) keyCodeDadoCript;
            dadoDescriptografado.append(dadoDescriptChar);
        }
        return dadoDescriptografado.toString();
    }
}
