package class16_practice.day2;

import class16.Edge;
import class16.Node;

import java.util.*;

public class DijKstra {

    /*
    return: Map<Node,Integer> result key 是节点，value 是从出发点到这个节点的最短路径
    param:Node start 出发点
    大致流程描述
        1、建立一张表map，保存出发点到 每个节点的最短路径，初始这个表为 put(start,0)
        2、老套路set 标识处理过的Node 点
        3、每次从map中获取 未处理过的最短路径的点，遍历所有的next节点 计算到next节点的最短路径， 计算的最短路径如果没有缓存添加缓存， 如果有缓存和缓存比较 使用最小值刷新缓存, 处理完毕基类set缓存
     */

    public Map<Node,Integer> dijKstra(Node start){
        Map<Node,Integer> result = new HashMap<>();
        Set<Node> set = new HashSet<>();
        result.put(start,0);

        Node node;
        while((node=getNode(result,set)) != null){
            set.add(node);
            for(Edge e:node.edges){
                Node toNode = e.to;
                if(result.containsKey(toNode)){
                    result.put(toNode,Math.min(result.get(toNode),result.get(node)+e.weight));
                }else {
                    result.put(toNode,result.get(node)+e.weight);
                }
            }
        }

        return result;
    }


    public Node getNode(Map<Node,Integer> map,Set<Node> set){

        int val = Integer.MAX_VALUE;
        Node node = null;
        Iterator<Map.Entry<Node, Integer>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Node, Integer> next = iterator.next();
            Node key = next.getKey();
            int value = next.getValue();
            if(!set.contains(key) && value < val){
                val = value;
                node = key;
            }
        }
        return node;
    }



}
