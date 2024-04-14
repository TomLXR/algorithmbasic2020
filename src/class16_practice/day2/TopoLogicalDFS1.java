package class16_practice.day2;

import java.util.*;

public class TopoLogicalDFS1 {

    public static class DirectedGraphNode{
        int label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x){
            label = x;
            neighbors = new ArrayList<>();
        }
    }


    public static class Record{
        public int deep;
        public DirectedGraphNode node;
        public Record(DirectedGraphNode node,int deep){
            this.node = node;
            this.deep = deep;
        }
    }

    public static Record f(DirectedGraphNode cur, Map<DirectedGraphNode,Record> map){
        if(map.containsKey(cur)){
            return map.get(cur);
        }

        int ans = 0;

        for(DirectedGraphNode next:cur.neighbors){
            ans = Math.max(f(next,map).deep,ans);
        }

        Record record = new Record(cur, ans + 1);
        map.put(cur,record);
        return record;
    }


    public static List<DirectedGraphNode> topoSort(List<DirectedGraphNode> list){
        Map<DirectedGraphNode,Record> map = new HashMap<>();
        for (DirectedGraphNode node:list){
            f(node,map);
        }

        List<Record> records = new ArrayList<>(map.values());
        Collections.sort(records,(a,b)-> b.deep-a.deep);
        List<DirectedGraphNode> ans = new ArrayList<>();
        for(Record record:records){
            ans.add(record.node);
        }

        return ans;
    }


}
