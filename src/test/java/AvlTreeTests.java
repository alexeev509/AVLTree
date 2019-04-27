import com.company.AvlTree;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class AvlTreeTests {
    Comparator comparator=new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    };
    AvlTree<Integer> avlTree;
    ArrayList<Integer> expectedValues;
    ArrayList<Integer> actualValues;

    @Test
    public void addValuesToTree_simpleLeftRotate(){
        expectedValues =new ArrayList<>(Arrays.asList(2,1,3));

        avlTree=new AvlTree<>(comparator);
        avlTree.add(1);
        avlTree.add(2);
        avlTree.add(3);
        actualValues = avlTree.getListOfValues();

        Assert.assertEquals(expectedValues,actualValues);

        actualValues=avlTree.getListOfBalances();
        expectedValues =new ArrayList<>(Arrays.asList(0,0,0));
        Assert.assertEquals(expectedValues,actualValues);
    }

    @Test
    public void addValuesToTree_simpleRightRotate(){
        expectedValues =new ArrayList<>(Arrays.asList(2,1,3));

        avlTree=new AvlTree<>(comparator);
        avlTree.add(3);
        avlTree.add(2);
        avlTree.add(1);
        actualValues = avlTree.getListOfValues();

        Assert.assertEquals(expectedValues,actualValues);

        actualValues=avlTree.getListOfBalances();
        expectedValues =new ArrayList<>(Arrays.asList(0,0,0));
        Assert.assertEquals(expectedValues,actualValues);
    }

    @Test
    public void addValuesToTree_LRRotate_left_right_balance_0(){
        expectedValues =new ArrayList<>(Arrays.asList(3,2,4));

        avlTree=new AvlTree<>(comparator);
        avlTree.add(4);
        avlTree.add(2);
        avlTree.add(3);
        actualValues = avlTree.getListOfValues();

        Assert.assertEquals(expectedValues,actualValues);

        actualValues=avlTree.getListOfBalances();
        expectedValues =new ArrayList<>(Arrays.asList(0,0,0));
        Assert.assertEquals(expectedValues,actualValues);
    }

    @Test
    public void addValuesToTree_LRRotate_left_right_balance_1(){
        expectedValues =new ArrayList<>(Arrays.asList(7,5,4,10,8,12));

        avlTree=new AvlTree<>(comparator);
        avlTree.add(10);
        avlTree.add(12);
        avlTree.add(5);
        avlTree.add(4);
        avlTree.add(7);
        avlTree.add(8);
        actualValues = avlTree.getListOfValues();

        Assert.assertEquals(expectedValues,actualValues);

        actualValues=avlTree.getListOfBalances();
        expectedValues =new ArrayList<>(Arrays.asList(0,-1,0,0,0,0));
        Assert.assertEquals(expectedValues,actualValues);
    }


    @Test
    public void addValuesToTree_LRRotate_left_right_balance_minus_1(){
        expectedValues =new ArrayList<>(Arrays.asList(7,5,4,6,10,12));

        avlTree=new AvlTree<>(comparator);
        avlTree.add(10);
        avlTree.add(12);
        avlTree.add(5);
        avlTree.add(4);
        avlTree.add(7);
        avlTree.add(6);
        actualValues = avlTree.getListOfValues();

        Assert.assertEquals(expectedValues,actualValues);

        actualValues=avlTree.getListOfBalances();
        expectedValues =new ArrayList<>(Arrays.asList(0,0,0,0,1,0));
        Assert.assertEquals(expectedValues,actualValues);
    }

    @Test
    public void addValuesToTree_RLRotate_right_left_balance_0(){
        expectedValues =new ArrayList<>(Arrays.asList(5,4,6));

        avlTree=new AvlTree<>(comparator);
        avlTree.add(4);
        avlTree.add(6);
        avlTree.add(5);
        actualValues = avlTree.getListOfValues();

        Assert.assertEquals(expectedValues,actualValues);

        actualValues=avlTree.getListOfBalances();
        expectedValues =new ArrayList<>(Arrays.asList(0,0,0));
        Assert.assertEquals(expectedValues,actualValues);
    }

    @Test
    public void addValuesToTree_RLRotate_right_left_balance_minus_1(){
        expectedValues =new ArrayList<>(Arrays.asList(10,7,5,9,11,12));

        avlTree=new AvlTree<>(comparator);
        avlTree.add(7);
        avlTree.add(5);
        avlTree.add(11);
        avlTree.add(10);
        avlTree.add(12);
        avlTree.add(9);
        actualValues = avlTree.getListOfValues();

        Assert.assertEquals(expectedValues,actualValues);

        actualValues=avlTree.getListOfBalances();
        expectedValues =new ArrayList<>(Arrays.asList(0,0,0,0,1,0));
        Assert.assertEquals(expectedValues,actualValues);
    }

    @Test
    public void addValuesToTree_RLRotate_right_left_balance_1(){
        expectedValues =new ArrayList<>(Arrays.asList(9,7,5,11,10,12));

        avlTree=new AvlTree<>(comparator);
        avlTree.add(7);
        avlTree.add(5);
        avlTree.add(11);
        avlTree.add(12);
        avlTree.add(9);
        avlTree.add(10);
        actualValues = avlTree.getListOfValues();

        Assert.assertEquals(expectedValues,actualValues);

        actualValues=avlTree.getListOfBalances();
        expectedValues =new ArrayList<>(Arrays.asList(0,-1,0,0,0,0));
        Assert.assertEquals(expectedValues,actualValues);
    }

    @Test
    public void addValuesToTree_DifficultExample(){
        expectedValues =new ArrayList<>(Arrays.asList(10,6,5,9,11,12));

        avlTree=new AvlTree<>(comparator);
        avlTree.add(6);
        avlTree.add(5);
        avlTree.add(11);
        avlTree.add(12);
        avlTree.add(10);
        avlTree.add(9);
        actualValues = avlTree.getListOfValues();

        Assert.assertEquals(expectedValues,actualValues);

        actualValues=avlTree.getListOfBalances();
        expectedValues =new ArrayList<>(Arrays.asList(0,0,0,0,1,0));
        Assert.assertEquals(expectedValues,actualValues);
    }

    @Test
    public void testRemove(){
        avlTree=new AvlTree<>(comparator);
        avlTree.add(6);
        avlTree.add(5);
        avlTree.add(11);
        avlTree.add(12);
        avlTree.add(10);
        avlTree.add(9);
        avlTree.remove(6);
    }

}
