/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salaboy.rolo.internals;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.Arrays;
import org.kie.runtime.rule.TypedAccumulateFunction;

/**
 *
 * @author salaboy
 */
public class TendencyAccumulateFunction implements TypedAccumulateFunction {


    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }


    public void writeExternal(ObjectOutput out) throws IOException {


    }


    public static class TendencyData implements Externalizable {


        public static int current;

        public static long history[];
        
        

        public TendencyData() {}


        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

            

            current   = in.readInt();
            
            history = (long[]) in.readObject();

        }


        public void writeExternal(ObjectOutput out) throws IOException {

            out.writeInt(current);
            
            out.writeObject(history);

        }


    }


    /* (non-Javadoc)

     * @see org.drools.base.accumulators.AccumulateFunction#createContext()

     */

    public Serializable createContext() {

        return new TendencyData();

    }


    /* (non-Javadoc)

     * @see org.drools.base.accumulators.AccumulateFunction#init(java.lang.Object)

     */

    public void init(Serializable context) throws Exception {

        TendencyData.history = new long[5];
        TendencyData.current = 0;
        

    }


    /* (non-Javadoc)

     * @see org.drools.base.accumulators.AccumulateFunction#accumulate(java.lang.Object, java.lang.Object)

     */

    public void accumulate(Serializable context,

                           Object value) {

        //TendencyData data = (TendencyData) context;

        TendencyData.current++;

        if(TendencyData.current > TendencyData.history.length - 1){
            TendencyData.current = 0;
        }
        
        TendencyData.history[TendencyData.current] = (Long) value;
        System.out.println("Current Pointer - "+TendencyData.current);
        System.out.println("Data History = "+Arrays.toString(TendencyData.history));
    }


    /* (non-Javadoc)

     * @see org.drools.base.accumulators.AccumulateFunction#reverse(java.lang.Object, java.lang.Object)

     */

    public void reverse(Serializable context,

                        Object value) throws Exception {


    }


    /* (non-Javadoc)

     * @see org.drools.base.accumulators.AccumulateFunction#getResult(java.lang.Object)

     */

    public Object getResult(Serializable context) throws Exception {

       // TendencyData data = (TendencyData) context;
        double tendency = 0.5;
        for(int i = 1; i < TendencyData.history.length; i ++){
            if(TendencyData.history[i] > TendencyData.history[i - 1]){
                
                tendency += ((double)1)/((double)TendencyData.history.length*10); 
                
            }else{
                tendency -= ((double)1)/((double)TendencyData.history.length*10); 
            }
        }
        return tendency;

    }


    /* (non-Javadoc)

     * @see org.drools.base.accumulators.AccumulateFunction#supportsReverse()

     */

    public boolean supportsReverse() {

        return false;

    }


    /**

     * {@inheritDoc}

     */

    public Class< ? > getResultType() {

        return Number.class;

    }


}
