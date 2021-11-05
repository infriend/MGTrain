import org.junit.Test;
import train.a3.BinaryTreeByArray;


public class TestRunner {
    @Test
    public void test1(){
        BinaryTreeByArray obj = null;
        obj = new BinaryTreeByArray(-1); //case1
        obj = new BinaryTreeByArray(0);  //case2
        obj = new BinaryTreeByArray(2147483643);  //case3
        obj = new BinaryTreeByArray(2147483647);  //case4
        obj = new BinaryTreeByArray(1);  //case5
        obj = new BinaryTreeByArray(20);  //case6
        obj = new BinaryTreeByArray(2147483642);  //case7
        //7 cases
    }

    @Test
    public void test2(){
        BinaryTreeByArray obj1 = new BinaryTreeByArray(4);
        obj1.insert(1); //case8
        obj1.insert(2147483647);  //case9
        obj1.levelordertraversal();
        BinaryTreeByArray obj2 = new BinaryTreeByArray(4);
        obj2.insert(-1);  //case10
        obj2.insert(0);  //case11
        BinaryTreeByArray obj3 = new BinaryTreeByArray(3);
        obj3.insert(1);
        obj3.insert(2);
        obj3.insert(3);
        obj3.insert(1);  //case12
    }

    @Test
    public void test3(){
        BinaryTreeByArray obj1 = new BinaryTreeByArray(4);
        obj1.insert(1);
        obj1.insert(2);
        obj1.insert(3);
        obj1.search(1);  //case13
        obj1.search(2);  //case14
        obj1.levelordertraversal();
        obj1.search(0);  //case15
        obj1.search(4);  //case16
        BinaryTreeByArray obj2 = new BinaryTreeByArray(4);
        obj2.search(0);  //case17
        obj2.search(4);  //case18
    }

    @Test
    public void test4(){
        BinaryTreeByArray obj1 = new BinaryTreeByArray(4);
        obj1.insert(1);
        obj1.insert(2);
        obj1.insert(3);
        obj1.delete(1);  //case19
        obj1.levelordertraversal();
        obj1.delete(3);  //case20
        obj1.levelordertraversal();
        BinaryTreeByArray obj2 = new BinaryTreeByArray(4);
        obj2.insert(1);
        obj2.insert(2);
        obj2.insert(3);
        obj1.delete(0);  //case21
        obj1.levelordertraversal();
        obj1.delete(4);  //case22
        obj1.levelordertraversal();
    }

    @Test
    public void test5(){
        BinaryTreeByArray obj1 = new BinaryTreeByArray(4);
        obj1.insert(1);
        obj1.insert(2);
        obj1.insert(3);
        System.out.println(obj1.lastusedindex);  //case23
    }

    @Test
    public void test6(){
        BinaryTreeByArray obj1 = new BinaryTreeByArray(4);
        obj1.insert(1);
        obj1.insert(2);
        obj1.insert(3);
        obj1.preorderTraversal(1);  //case24
        System.out.println(" ");
        obj1.preorderTraversal(0);  //case25
        System.out.println(" ");
        obj1.inorderTraversal(1);  //case26
        System.out.println(" ");
        obj1.inorderTraversal(0);  //case27
        System.out.println(" ");
        obj1.postorderTraversal(1);  //case28
        System.out.println(" ");
        obj1.postorderTraversal(0);  //case29
        System.out.println(" ");
    }
}
