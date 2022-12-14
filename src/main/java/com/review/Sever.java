package com.review;

import Mahoa.AES;
import Mahoa.RSA;
import com.review.models.Product;
import com.review.models.Tiki;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.List;

public class Sever  {
    private int buffsize ;
    private int port;
    public static DatagramSocket socket;
    public static DatagramPacket dpreceive, dpsend;
    static RSA rsa=new RSA();
    private static HashMap<String,AES> listip=new HashMap<>();
    public Sever(int port,int buffsize) throws SocketException {
        this.socket = new DatagramSocket(port);
        this.dpreceive = new DatagramPacket(new byte[buffsize], buffsize);
    }
    public void Send(String value) throws IOException {
        dpsend = new DatagramPacket(value.getBytes(), value.getBytes().length,dpreceive.getAddress(),dpreceive.getPort());
        System.out.println("Sever send :");
        System.out.println(value);
        socket.send(dpsend);
    }
    public void SendList(List<Product> list,String ip){
        AES aes=listip.get(ip);
        try {
            Send(aes.encryptList(list));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String[] Receive(String tmp)throws IOException{
        String[] token = tmp.split("#");
        return token;
    }
    public void CheckRequest(String tmp) throws IOException{
        String[] token = Receive(tmp);
        if(token[0].equals("search")){
            SearchProduct(token[1]);
        }
    }
    public void SearchProduct(String query)throws IOException{
        Tiki tiki = new Tiki();
        SendList(tiki.getProductsByQuery(query),dpreceive.getAddress().getHostAddress());
    }
    public void ConnectSever() throws IOException {
        try {
            rsa.createkey();
            while(true){
                socket.receive(dpreceive);
                String tmp = new String(dpreceive.getData(), 0 , dpreceive.getLength());
                if(listip.containsKey(dpreceive.getAddress().getHostAddress())){
                    AES aes=listip.get(dpreceive.getAddress().getHostAddress());
                    tmp=aes.decrypt(tmp);
                    if(tmp.equals("bye")){
                        System.out.println(listip.get(dpreceive.getAddress().getHostAddress())+" has disconnected");
                        listip.remove(dpreceive.getAddress().getHostAddress());
                    }
                    else {
                        System.out.println("Server receive "+ tmp);
                        CheckRequest(tmp);
                    }
                }
                else {
                    traodoikey();
                }

            }
        }
        catch (IOException e) {
            System.err.println(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }

    }
    public static void traodoikey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        dpsend = new DatagramPacket(rsa.publickey.getEncoded(), rsa.publickey.getEncoded().length,dpreceive.getAddress(),dpreceive.getPort());
        socket.send(dpsend);
        socket.receive(dpreceive);
        AES aes=new AES();
        String key=new String(dpreceive.getData(),0,dpreceive.getLength());
        key=rsa.giaima(key);
        String[] token=key.split(";");
        aes.setPassword(token[0]);
        aes.setSalt(token[1]);
        aes.createkey();
        listip.put(dpreceive.getAddress().getHostAddress(), aes);
    }
    public static void main(String[] args) throws IOException
    {
            Sever sv = new Sever(1234, 512);
        while(true) {
            sv.ConnectSever();
        }
    }
}
